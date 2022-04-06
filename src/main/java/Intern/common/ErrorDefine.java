package Intern.common;

public enum ErrorDefine {
    S_200("200 OK"),
    S_500("500 Internal Server Error"),
    S_207("207 Sai dữ liệu data"),
    S_403("403 Forbidden"),
    S_404("404 Not Found");

    private String error;
    ErrorDefine(String errorMessage){
        this.error = errorMessage;
    }

    public String getError(){
        return this.error;
    }

}