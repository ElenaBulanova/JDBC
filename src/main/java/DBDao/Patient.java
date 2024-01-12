package DBDao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Patient {
    private final Integer num;
    private final String snils;
    private final String sex;
    private final String fio;
    private final LocalDate birthDate;
    private final String policy;
    private final int finSource;
    private final List<Integer> expenses = new ArrayList<>();


    public String getFio() {
        return fio;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public List<Integer> getExpenses() {
        return expenses;
    }

    public String getSex() {
        return sex;
    }

    public Integer getNum() {
        return num;
    }

    public String getSnils() {
        return snils;
    }

    public String getPolicy() {
        return policy;
    }

    public int getFinSource() {
        return finSource;
    }


    public Patient(String fio, LocalDate birthDate, Integer sex,
                   Integer num, String smo, String snils, String policy, Integer finSource) {
        this.fio = fio;
        this.birthDate = birthDate;
        this.sex = PatientAdapter.getSexStr(sex);
        this.num = num;
        this.snils = PatientAdapter.getSnilsStr(snils);
        this.policy = PatientAdapter.getPolicyStr(smo, policy);
        this.finSource = finSource;
    }




    @Override
    public String toString() {
        return "DBDao.Patient{" +
//                    "id=" + id +
                ", num=" + num +
                ", snils='" + snils + '\'' +
                ", sex='" + sex + '\'' +
                ", fio='" + fio + '\'' +
                ", birthDate=" + birthDate +
//                    ", company='" + company + '\'' +
                ", policy='" + policy + '\'' +
                ", finSource='" + finSource + '\'' +
                "}\n";
    }

}
