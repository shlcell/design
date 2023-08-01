package login.implementor.abstractlogin;

import login.implementor.LoginFunc;

public abstract class AbstractLoginProcessor {
    protected LoginFunc loginFunc;

    public AbstractLoginProcessor(LoginFunc loginFunc) {
        this.loginFunc = loginFunc;
    }

    public abstract boolean loginExecute(String name, String pwd, String type);
}
