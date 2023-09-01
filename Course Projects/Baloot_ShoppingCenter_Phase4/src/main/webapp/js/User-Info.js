class UserInfo extends React.Component {
    constructor(props) {
        super(props);
        this.state = { user_name: "" ,
            user_email:     "",
            user_birthDate: "",
            user_address:   "",
            user_credit:    0};
    }

    fetch_user_info_from_API() {
        const response = fetch('/getUserInfo?username=' + this.props.username)
            .then(response => response.json())
            .then(response => this.setState(prevState => ({ user_name : response._username,
                user_birthDate: response._birthDate,
                user_address: response._address,
                user_credit: response._credit,
                user_email: response._email})));
    }

    componentDidMount() {
        this.fetch_user_info_from_API();
    }

    confirm_add_to_credit() {
        const add_credit_amount = document.getElementById("add-credit-amount").value;
        document.getElementById("add-credit-form-body").innerHTML =
            "Are you sure you want to add " + add_credit_amount.toString() + "$ to your current account";
        document.getElementById("add-credit-form-footer").innerHTML =
            '<div class="confirm-add-to-credit">' +
                '<button data-dismiss="modal">Close</button>' +
                '<a href="user?username=' + this.state.user_name + '&add_credit=' + add_credit_amount + '"><button >Confirm!</button></a>' +
            '</div>';
    }

    render() {
        const user_name = this.state.user_name;
        const user_email = this.state.user_email;
        const user_credit = this.state.user_credit;
        const user_address = this.state.user_address;
        const user_birthDate = this.state.user_birthDate;
        return (
            <div className="add-credit-form-container">
                <div className="user-info">
                    <ul>
                        <li><i className="bi bi-person-fill"></i>&nbsp;&nbsp;{user_name}</li>
                        <li><i className="bi bi-envelope-fill"></i>&nbsp;&nbsp;{user_email}</li>
                        <li><i className="bi bi-calendar2"></i>&nbsp;&nbsp;{user_birthDate}</li>
                        <li><i className="bi bi-geo-alt-fill"></i>&nbsp;&nbsp;{user_address}</li>
                    </ul>
                    <p>${user_credit}</p>
                    <div className="credit-button"> <button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$Amount&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</button>
                        <br />
                        <button data-toggle="modal" data-target="#add-credit-form">Add More Credit&nbsp;</button>
                    </div>
                </div>
                <div className="modal fade" id="add-credit-form" role="dialog">
                    <div className="modal-dialog modal-sm">
                        <div className="modal-content">
                            <div className="modal-header">
                                <h5 className="modal-title">Add Credit</h5>
                                <button type="button" className="close" data-dismiss="modal">&times;</button>
                            </div>
                            <div className="modal-body" id="add-credit-form-body">
                                <p>Enter the credit you want to add</p>
                                <div className="add-credit-form">
                                    <input type="text" id="add-credit-amount"/>
                                    <button onClick={this.confirm_add_to_credit.bind(this)}>Add</button>
                                </div>
                            </div>
                            <div className="modal-footer" id="add-credit-form-footer"></div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}