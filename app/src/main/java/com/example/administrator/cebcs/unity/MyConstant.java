package com.example.administrator.cebcs.unity;

/**
 * Created by ASUS on 12/9/2560.
 */

public class MyConstant {

    private String urlGetNotiWhereIdSubject = "http://androidthai.in.th/12Sep/getNotificationWhereIdStudent.php";

    private String urlGetNotificationWhereIdStudentString = "http://androidthai.in.th/12Sep/getNotificationWhereIdStudent.php";

    private String urlPostUserString = "http://androidthai.in.th/12Sep/addUserChris.php";

    private String urlgetUserString = "http://androidthai.in.th/12Sep/getAllUserChris.php";

    private String urlGetSubject = "http://androidthai.in.th/12Sep/getAllSubject.php";

    private String urlGetCE = "http://androidthai.in.th/12Sep/getAllCE.php";

    private String urlGetCEwhereIDstudent = "http://androidthai.in.th/12Sep/getCEWhereIDstudent.php";

    private String urlEditPassWordWhereIdStudent = "http://androidthai.in.th/12Sep/editPasswordWhereIdStudent.php";

    private String urlGetSubjectWhereIdSub = "http://androidthai.in.th/12Sep/getSubjectWhereCE.php";

    private String[] columnUserStrings = new String[]{
            "id",
            "idStudent",
            "idSt2",
            "Name",
            "Surname",
            "Gender",
            "Password",
            "Major",
            "Sector",
            "Class"
    };

    private String[] majorStrings = new String[]{
            "คอมพิวเตอร์ธุรกิจ",
            "บัญชี",
            "การตลาด",
            "การจัดการ"
    };

    private String[] sectorStrings = new String[]{
            "วันอาทิตย์",
            "ค่ำ"
    };

    private String[] classStrings = new String[]{
            "ห้อง 1/1",
            "ห้อง 1/2",
            "ห้อง 1/3",
            "ห้อง 1/4",
            "ห้อง 2/1",
            "ห้อง 2/2",
            "ห้อง 2/3",
            "ห้อง 2/4"
    };


    public String getUrlGetNotiWhereIdSubject() {
        return urlGetNotiWhereIdSubject;
    }

    public String getUrlGetNotificationWhereIdStudentString() {
        return urlGetNotificationWhereIdStudentString;
    }

    public String getUrlGetSubjectWhereIdSub() {
        return urlGetSubjectWhereIdSub;
    }

    public String getUrlEditPassWordWhereIdStudent() {
        return urlEditPassWordWhereIdStudent;
    }

    public String getUrlGetCEwhereIDstudent() {
        return urlGetCEwhereIDstudent;
    }

    public String getUrlGetSubject() {
        return urlGetSubject;
    }

    public String getUrlGetCE() {
        return urlGetCE;
    }

    public String[] getMajorStrings() {
        return majorStrings;
    }

    public String[] getSectorStrings() {
        return sectorStrings;
    }

    public String[] getClassStrings() {
        return classStrings;
    }

    public String[] getColumnUserStrings() {
        return columnUserStrings;
    }

    public String getUrlgetUserString() {
        return urlgetUserString;
    }

    public String getUrlPostUserString() {
        return urlPostUserString;
    }
}   // Main Class
