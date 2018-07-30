package garage.assistant.settings;

import com.google.gson.Gson;
import garage.assistant.alert.AlertMaker;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.digest.DigestUtils;

public class Preferences {
    public static final String CONFIG_FILE = "config.txt";
    
    //default values
    int nDaysWithoutFine;
    float finePerDay;
    String username;
    String password;
    
    //constructor, set all values to default
    public Preferences() {
        nDaysWithoutFine = 5;
        finePerDay = 500000;
        username = "tuan";
        setPassword("root");//hashed
    }

    public int getnDaysWithoutFine() {
        return nDaysWithoutFine;
    }

    public void setnDaysWithoutFine(int nDaysWithoutFine) {
        this.nDaysWithoutFine = nDaysWithoutFine;
    }

    public float getFinePerDay() {
        return finePerDay;
    }

    public void setFinePerDay(float finePerDay) {
        this.finePerDay = finePerDay;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {//assign a security method
        if(password.length() < 16)//valid
            this.password = DigestUtils.shaHex(password);//hash then store the pass
        else//not hashing a hashed pass
            this.password = password;
    }
    
    //whenever run app for the first time -> no config file -> create a config file with a default values
    public static void initConfig() {
        Writer writer = null;
        try {
            Preferences preference = new Preferences();
            Gson gson = new Gson();//converting an object into gson 'string'
            writer = new FileWriter(CONFIG_FILE);
            gson.toJson(preference, writer);
        } catch (IOException ex) {//can not convert
            Logger.getLogger(Preferences.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();//close the writer
            } catch (IOException ex) {
                Logger.getLogger(Preferences.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static Preferences getPreferences() {
        Gson gson = new Gson();
        Preferences preferences = new Preferences();
        try {//read from file
            preferences = gson.fromJson(new FileReader(CONFIG_FILE), Preferences.class);
        } catch (FileNotFoundException ex) {//not found an existing file
            initConfig();//than create one
            Logger.getLogger(Preferences.class.getName()).log(Level.SEVERE, null, ex);
        }
        return preferences;
    }
    
    public static void writePreferencesToFile(Preferences preference) {//write the current preference
        Writer writer = null;
        try {
            Gson gson = new Gson();//converting an object into gson 'string'
            writer = new FileWriter(CONFIG_FILE);
            gson.toJson(preference, writer);
            
            AlertMaker.showSimpleAlert("Success", "Settings updated ");
        } catch (IOException ex) {
            Logger.getLogger(Preferences.class.getName()).log(Level.SEVERE, null, ex);
            AlertMaker.showErrorMessage(ex, "Failed", "Cant save the configuration file");
        } finally {
            try {
                writer.close();//close the writer
            } catch (IOException ex) {
                Logger.getLogger(Preferences.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
