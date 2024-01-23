import dao.BankDAO;
import model.Account;

import java.util.List;
import java.util.Scanner;

public class BankApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // http://bank.com/account GET
        // http://bank.com/account/10 GET
        // http://bank.com/account POST
        // http://bank.com/account/1 DELETE
        // http://bank.com/account/1 PUT

        System.out.print("http 메서드를 입력하세요: ");
        String method = sc.nextLine();
        System.out.print("식별자를 입력하세요: ");
        String action = sc.nextLine();

        String body = "";

        BankDAO bankDAO = new BankDAO();
        if (method.equals("GET")) {
            if (action.equals("/account")) {
                List<Account> accountList = bankDAO.selectAll();
                System.out.println(accountList);
            } else if (action.equals("/account/1")) {
                Account account = bankDAO.selectByNumber(6);
                System.out.println(account);
            }
        } else if (method.equals("POST")) {
            System.out.println("body 데이터를 입력하세요.");
            body = sc.nextLine();

            // password=1234&balance=1000
            String[] st1 = body.split("&");
            String password = st1[0].split("=")[1];
            int balance = Integer.parseInt(st1[1].split("=")[1]);

            if (action.equals("/account")) {
                bankDAO.insert(password, balance);
                System.out.println("등록 완료!");
            }
        } else if (method.equals("PUT")) {
            int number = Integer.parseInt(action.replaceAll("[^0-9]", ""));
            System.out.print("금액을 입력하세요: ");
            int updateBalance = sc.nextInt();
            bankDAO.updateByNumber(number, updateBalance);
            System.out.println("수정 완료!");
            Account account = bankDAO.selectByNumber(number);
            System.out.println(account);

        } else if (method.equals("DELETE")) {
            int number = Integer.parseInt(action.replaceAll("[^0-9]", ""));
            bankDAO.deleteByNumber(number);
            System.out.println("삭제 완료!");
        }
    }
}
