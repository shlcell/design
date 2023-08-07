package login.implementor.abstractlogin;

import login.implementor.LoginFunc;

// 桥接模式：第三方登录
public abstract class AbstractLoginProcessor {
    protected LoginFunc loginFunc;

    public AbstractLoginProcessor(LoginFunc loginFunc) {
        this.loginFunc = loginFunc;
    }

    public abstract boolean loginExecute(String name, String pwd, String type);
}
