package dao;

import model.Account;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * DAO - Data Access Object
 * SRP - 단일책임의 원칙
 */
public class BankDAOTest {

    @Test
    public void selectAll_test(){
        // given

        // when
        BankDAO dao = new BankDAO();
        List<Account> accountList = dao.selectAll();

        System.out.println(accountList.size());
        for(Account account : accountList){
            System.out.println(account);
        }
    }

    @Test
    public void  selectByNumber_test(){
        int number = 5;

        BankDAO dao = new BankDAO();
        Account account = dao.selectByNumber(number);

        if (account == null){
            System.out.println(number + "로 조회된 값이 없습니다.");
        }else {
            System.out.println(account);
        }
    }

    @Test
    public void deleteByNumber_test() {
        // given
        int number = 4;

        // when
        BankDAO dao = new BankDAO();
        int result = dao.deleteByNumber(number);

        // then
        if (result == 1) {
            System.out.println("삭제 성공!!!");
        } else if (result == 0) {
            System.out.println(number + "번호를 찾을 수 없습니다.");
        } else {
            System.out.println("삭제 실패!!!");
        }
    }

    @Test
    public void insert_test() {
        // given
        String password = "8585";
        int balance = 3500;

        // when
        BankDAO dao = new BankDAO();
        int result = dao.insert(password, balance);

        // then
        if (result == 1) {
            System.out.println("등록 성공!!!");
        } else {
            System.out.println("등록 실패!!!");
        }
    }

    @Test
    public void updateByNumber_test() {
        // given
        int balance = 9000;
        int number = 7;

        // when
        BankDAO dao = new BankDAO();
        int result = dao.updateByNumber(balance, number);

        // then
        if (result == 1) {
            System.out.println("수정 성공!!!");
        } else {
            System.out.println("수정 실패!!!");
        }
    }




}
