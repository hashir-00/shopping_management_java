import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class User {

    private String username;
    private String password;

    private final static File userFile = new File("UserProfile.txt");//creating a file
    HashMap<String,String> usersList=new HashMap<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;

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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsers(String username,String password){

        usersList.put(username.toUpperCase(),password);
        saveUserName_password();

    }

    public void saveUserName_password() {

      BufferedWriter bf = null;
      try {

          // create new BufferedWriter for the output file
          bf = new BufferedWriter(new FileWriter(userFile,true));

          // iterate map entries
          for (Map.Entry<String, String> entry :
                  usersList.entrySet()) {

              // put key and value separated by a colon
              bf.write(entry.getKey() + ":"
                      + entry.getValue());

              // new line
              bf.newLine();
          }

          bf.flush();
      }
      catch (IOException e) {
          e.printStackTrace();
      }
      finally {

          try {

              // always close the writer
              bf.close();
          }
          catch (Exception e) {
          }

      }
}
    public boolean checkUsername_password(String username,String password){
        loadUserdetails();
        for (Map.Entry<String, String> user :
               usersList.entrySet()){
            if (username.equals(user.getKey()) && password.equals(user.getValue())){
                return true;
            }


        }
        return false;
    }



    private void loadUserdetails(){
    BufferedReader br = null;
    try {

        // create file object


        // create BufferedReader object from the File
   br = new BufferedReader(new FileReader(userFile));

        String line = null;

        // read file line by line
        while ((line = br.readLine()) != null) {

            // split the line by :
            String[] parts = line.split(":");

            // first part is name, second is number
            String name = parts[0].trim();
            String number = parts[1].trim();

            // put name, number in HashMap if they are
            // not empty
            if (!name.equals("") && !number.equals(""))
                usersList.put(name, number);
        }
    }
    catch (Exception e) {
        e.printStackTrace();
    }
    finally {

        // Always close the BufferedReader
        if (br != null) {
            try {
                br.close();
            }
            catch (Exception e) {
            };
        }
    }


}
}
