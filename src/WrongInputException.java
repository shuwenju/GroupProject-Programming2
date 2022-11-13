public class WrongInputException extends Exception {

    WrongInputException(String message){
        super("Wrong Input Exception:"+message );
    }
}
