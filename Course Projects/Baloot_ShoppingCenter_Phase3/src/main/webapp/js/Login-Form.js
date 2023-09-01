function LoginForm() {

    function login_request_event_handler(){
        window.location.href = "authentication?username=" + document.getElementById("input_username").value + "&password=" + document.getElementById("input_password").value;
    };

    function create_new_account_event_handler(){
        window.location.href = "authentication?create_account=1&username=" + document.getElementById("input_username").value + "&password=" + document.getElementById("input_password").value;
    };

    return (
        <div className="container h-100">
            <div className="d-flex justify-content-center h-100">
                <div className="user_card">
                    <div className="d-flex justify-content-center">
                        <div className="brand_logo_container">
                            <img src="photo/Baloot_logo.svg" className="brand_logo" alt="Logo"/>
                        </div>
                    </div>
                    <div className="d-flex justify-content-center form_container">
                        <form>
                            <div className="input-group mb-3">
                                <div className="input-group-append">
                                    <span className="input-group-text"><i className="fas fa-user"></i></span>
                                </div>
                                <input type="text" id="input_username" className="form-control input_user" placeholder="Username"/>
                            </div>
                            <div className="input-group mb-2">
                                <div className="input-group-append">
                                    <span className="input-group-text"><i className="fas fa-key"></i></span>
                                </div>
                                <input type="text" id="input_password" className="form-control input_pass" placeholder="Password"/>
                            </div>
                            <div className="d-flex justify-content-center mt-3 login_container">
                                <a onClick={login_request_event_handler}><button type="button" className="btn login_btn">Login</button></a>
                            </div>
                        </form>
                    </div>
                    <div className="mt-4">
                        <div className="d-flex justify-content-center links">
                            <a onClick={create_new_account_event_handler} className="create_account_link">Create New Account</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}