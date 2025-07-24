package validate;

import presentation.color.Color;

import java.util.regex.Pattern;

public class Validator {
    public static boolean inputIsInteger(String str) {
        if (str == null || str.trim().isEmpty()) {
            System.out.println(Color.ANSI_RED_BACKGROUND + "Bạn đang để trống, vui lòng nhập lại" + Color.ANSI_RESET);
            return false;
        }
        try {
            int value = Integer.parseInt(str.trim());
            if (value >= 0) {
                return true;
            } else {
                System.out.println(Color.ANSI_RED_BACKGROUND + "Số bạn nhập phải là số nguyên dương" + Color.ANSI_RESET);
                return false;
            }

        } catch (NumberFormatException e) {
            System.out.println(Color.ANSI_RED_BACKGROUND + "Bạn đã không nhập vào số nguyên, vui lòng nhập lại" + Color.ANSI_RESET);
            return false;
        }

    }

    public static boolean inputNotEmpty(String str) {
        if (str == null || str.trim().isEmpty()) {
            System.out.println(Color.ANSI_RED_BACKGROUND + "Bạn đang để trống, vui lòng nhập lại" + Color.ANSI_RESET);
            return false;
        }
        return true;
    }

    public static boolean inputIsPhoneNumber(String str) {
        if (str == null || str.trim().isEmpty()) {
            System.out.println(Color.ANSI_RED_BACKGROUND + "Bạn đang để trống, vui lòng nhập lại" + Color.ANSI_RESET);
            return false;
        }
        String phoneNumberRegex = "^(032|033|034|035|036|037|038|039|096|097|098|086|083|084|085|081|082|088|091|094|070|079|077|076|078|090|093|089|056|058|092|059|099)[0-9]{7}$";
        if(Pattern.matches(phoneNumberRegex, str)) {
            return true;
        }else {
            System.out.println(Color.ANSI_RED_BACKGROUND+"Số điện thoại không đúng định dạng, vui lòng nhập lại"+Color.ANSI_RESET);
            return false;
        }
    }

    public static boolean passwordLegit(String str) {
        if (str == null || str.trim().isEmpty()) {
            System.out.println(Color.ANSI_RED_BACKGROUND + "Bạn đang để trống, vui lòng nhập lại" + Color.ANSI_RESET);
            return false;
        }
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z]).{6,}$";
        if (Pattern.matches(passwordRegex, str)) {
            return true;
        }else {
            System.out.println(Color.ANSI_RED+"Mật khẩu chưa đúng định dạng, vui lòng nhập lại"+Color.ANSI_RESET);
            return false;
        }
    }
    public static boolean emailLegit(String str) {
        if (str == null || str.trim().isEmpty()) {
            System.out.println(Color.ANSI_RED_BACKGROUND + "Bạn đang để trống, vui lòng nhập lại" + Color.ANSI_RESET);
            return false;
        }
        String emailRegex = "^[a-zA-Z0-9_.]+@[a-zA-Z0-9-]+\\.[a-z]+$";
        if (Pattern.matches(emailRegex, str)) {
            return true;
        }else {
            System.out.println(Color.ANSI_RED+"Email chưa đúng định dạng, vui lòng nhập lại"+Color.ANSI_RESET);
            return false;
        }
    }
}
