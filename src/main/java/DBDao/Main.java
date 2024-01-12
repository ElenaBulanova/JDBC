package DBDao;


import java.util.List;

public class Main {
    public static void main(String[] args) {

        Dao dao = new Dao();

//        List<Patient> all = dao.getAll();
//        all.forEach(System.out::println);

//        List<Patient> women = dao.queryByString("SELECT * FROM PATIENTS WHERE sex = 2");
//        women.forEach(System.out::println);

//        System.out.println(dao.getMenFiSource3Birthdate_Fio());

//        пациенты старше 70 лет
//        System.out.println(dao.queryByString("select  * from patients where TIMESTAMPDIFF(YEAR, birth_date, CURDATE()) > 70  ;"));

//        мужчины, застрахованные в ВСК
//        System.out.println(dao.queryByString("select * from patients where sex = 1 && smo = 'ВСК';"));

//        количество мужчин и женщин
        System.out.println("Количество мужчин и женщин:");
        System.out.println(dao.getCountMF());

//        количество человек по страховым компаниям
        System.out.println("Количество человек по страховым компаниям:");
        System.out.println(dao.getSmo());

        dao.closeConnection();
    }
}

