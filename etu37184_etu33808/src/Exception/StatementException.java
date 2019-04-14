package Exception;

public class StatementException extends Exception {
    private String methodeName;
    private String packageName;

    public StatementException(String methodeName, String packageName){
        this.methodeName = methodeName;
        this.packageName = packageName;
    }

    public String getMessage(){
        return "Statement Exception from " + methodeName +  " in " + packageName;
    }
}
