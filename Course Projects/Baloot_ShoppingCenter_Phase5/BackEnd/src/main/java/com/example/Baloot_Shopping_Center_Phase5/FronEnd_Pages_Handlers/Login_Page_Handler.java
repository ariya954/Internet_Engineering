package com.example.Baloot_Shopping_Center_Phase5.FronEnd_Pages_Handlers;

public class Login_Page_Handler {
    public String Response() {
        String Response = "<!Doctype html>" +
                "<html lang=\"en\">" +

                    "<head>" +
                        "<title>Baloot</title>" +
                        "<link rel=\"stylesheet\" href=\"css/Login - page - css.css\">" +
                        "<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">" +
                        "<link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.6.1/css/all.css\" integrity=\"sha384-gfdkjb5BdAXd+lj+gudLWI+BXq4IuLW5IT+brZEZsLFm++aCMlF1V92rMkPaX4PP\" crossorigin=\"anonymous\">" +
                        "<link rel=\"stylesheet\" href=\"//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css\" id=\"bootstrap-css\">" +
                        "<script src=\"https://unpkg.com/react@16/umd/react.development.js\"></script>" +
                        "<script src=\"https://unpkg.com/react-dom@16/umd/react-dom.development.js\"></script>" +
                        "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/babel-standalone/6.10.3/babel.min.js\"></script>" +
                        "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>" +
                        "<script src=\"//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js\"></script>" +
                        "<script src=\"//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>" +
                        "<script type=\"text/babel\" src=\"js/Login-Form.js\"></script>" +
                    "</head>" +

                    "<body>" +

                        "<div id=\"login-form\"></div>" +

                        "<script type=\"text/babel\">" +
                            "ReactDOM.render(<LoginForm />, document.getElementById(\'login-form\'));" +
                        "</script>" +

                    "</body>" +

                "</html>";
        return Response;
    }
}