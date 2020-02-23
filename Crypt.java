package your.package.here;
import android.os.Build;
import java.util.Base64;

public class Crypt {
    public static String txt = "Your message to encode";
    public static String key = "your-key";
    public static String rev(String input) {
        byte[] strAsByteArray = input.getBytes();
        byte[] result
                = new byte[strAsByteArray.length];
        for (int i = 0; i < strAsByteArray.length; i++) {
            result[i]
                    = strAsByteArray[strAsByteArray.length - i - 1];
        }
        return new String(result);
    }
    public static String cesar(String s, int n, boolean rv) {
        String[] m = new String[2];
        m[0] = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
        m[1] = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int sm;
        String r = s;
        for (int a = 0; a <= 1; a++) {
            String mt = m[a];
            int p = mt.indexOf(s);
            if (p >= 0) {
                if (rv == true) {
                    sm = p - n;
                } else {
                    sm = p + n;
                }
                while (sm >= 52) {
                    sm -= 26;
                }
                while (sm < 0) {
                    sm += 26;
                }
                r = String.valueOf(mt.charAt(sm));
            }
        }
        return r;
    }
    public static int charVal(String c) {
        String m = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int p = m.indexOf(c);
        if (p > 0) {
            return p;
        }
        return 0;
    }
    public static String viginere(String s, String k, boolean rv) {
        int kl = k.length();
        int cn = 0;
        String r = "";
        for (int i = 0; i < s.length(); i++) {
            if (cn == kl) {
                cn = 0;
            }
            String ss = String.valueOf(k.charAt(cn));

            int int_ = charVal(ss);
            String cs = "";
            String si = String.valueOf(s.charAt(i));
            cs = cesar(si, int_, rv);
            r += cs;
            cn++;
        }
        return r;
    }
    public static String sh(String s, int d) {
        int g = 1, l = s.length();
        String[] ns = new String[l];
        for (int i = 0; i < l; i++) {
            if (g > d) {
                g = 1;
            }
            if (ns[g]==null) {
                ns[g]="";
            }
            String str=String.valueOf(s.charAt(i));
            if (s.charAt(i)!=-1) {
                ns[g] += str;
            }
            g++;
        }
        String r = "";
        for (int a = 1; a <= d; a++) {
            if (ns[a]!=null) {
                r += ns[a] + "";
            }

        }
        return r;
    }
    public static String unsh(String s, int d) {
        int c = 0, a = 0;
        int l = s.length();
        char[] arr = new char[l];
        for (int i = 0; i < l; i++) {
            if (s.charAt(i)!=-1) {
                arr[c] = s.charAt(i);
            }
            if (c < (l - d)) {
                c += d;
            } else {
                a++;
                c = a;
            }
        }
        String r = "";
        for (int i = 0; i < l; i++) {
            if (arr[i]!=-1) {
                String ss=String.valueOf(arr[i]);
                r += ss + "";
            }
        }
        return r;
    }
    public static String b64encode(String s) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return Base64.getEncoder().encodeToString(s.getBytes());
        }
        return "";
    }
    public static String b64decode(String s) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return new String(Base64.getDecoder().decode(s));
        }
        return "";
    }
    public static void printAll(){
        String phrase=txt;
        String sh3=sh(phrase,3);
        System.out.println("SHUFFLE:   "+sh3);
        String unsh3=unsh(sh3,3);
        System.out.println("UNSHUFFLE: "+unsh3);

        String b64e=b64encode(phrase);
        System.out.println("B64 ENC:   "+b64e);
        String b64d=b64decode(b64e);
        System.out.println("B64 DEC:   "+b64d);
    }
    //public static void main(String[] args) {
        //printAll();
    //}
}
