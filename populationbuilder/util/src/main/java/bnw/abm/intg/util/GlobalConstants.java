package bnw.abm.intg.util;

/**
 * @author Bhagya N. Wickramasinghe
 *
 */
public class GlobalConstants {

    public enum EXITCODE {
        NORMAL(0, "All Good!"),
        IOERROR(1, "Input/output error"),
        ENVVAR(2, "Environment variable read error"),
        USERINPUT(3, "User input error"),
        PROGERROR(4, "Programmar error"),
        UNDEF(99, "Undefined error");

        private int code;
        private String msg;

        EXITCODE(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return this.code;
        }

        public String getMsg(){
            return this.msg;
        }
    }

    public static int EXIT_NORMAL = 0;
    public static int EXIT_IOERROR = 1;
}
