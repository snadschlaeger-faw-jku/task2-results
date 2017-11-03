import repo.MySqlATMRepository;
import util.BruteForceIE;

import java.sql.*;
import java.util.Arrays;


public class Run {

    public static void main(String[] args){
        MySqlATMRepository repo;
        BruteForceIE bruteForceIE;
        int n = 4;
        assert ((n & (n-1)) == 0);
        String url = "jdbc:mysql://localhost:3306/knowledge_db";
        String user = "root";
        String passwd = "root";

        try{
            repo = new MySqlATMRepository(url, user, passwd);
            bruteForceIE = new BruteForceIE(repo, Arrays.asList("Account", "CreditCard", "Pin"));
            repo.close();

        }catch (Exception exc){
            exc.printStackTrace();
        }


    }
}
