class Pay extends React.Component {
    constructor(props) {
        super(props);
        this.state = ({ user_buy_list: [] });
    }

    fetch_user_buy_list_from_API() {
        const response = fetch('/getUserBuyList?username=' + this.props.username)
            .then(response => response.json())
            .then(response => this.setState(prevState => ({ user_buy_list : response})));
    }

    componentDidMount() {
        this.fetch_user_buy_list_from_API();
    }

    get_total_price() {
        let total_price = 0;
        for(let i = 0; i < this.state.user_buy_list.length; i++)
            total_price += this.state.user_buy_list.at(i).price * this.state.user_buy_list.at(i).in_cart;
        return total_price;
    }

    user_buy_list_items() {
        const buy_list_items = this.state.user_buy_list.map((buy_list_item, idx) =>
                <li key={idx} className="buy-list-item">
                    {buy_list_item.name} * {buy_list_item.in_cart}
                    <span className="price"> {buy_list_item.price * buy_list_item.in_cart}$</span>
                </li>
            );
        return <div>{buy_list_items}</div>;
    }

    render() {
        return (
            <div className="buy-list-form-container">
                <div className="pay_button">
                    <button id="PayButton" data-toggle="modal" data-target="#buy-list-form">Pay Now!</button>
                </div>
                <div className="modal fade" id="buy-list-form" role="dialog">
                    <div className="modal-dialog modal-sm">
                        <div className="modal-content">
                            <div className="modal-header">
                                <h5 className="modal-title">Your Cart</h5>
                                <button type="button" className="close" data-dismiss="modal">&times;</button>
                            </div>
                            <div className="modal-body">
                                <ul>
                                    {this.user_buy_list_items()}
                                </ul>
                                <div className="discount-form">
                                    <input type="text" id="opinion" placeholder="Code"/>
                                        <button data-toggle="modal" data-target="#confirm-add-to-credit">Submit</button>
                                </div>
                                <div className="total-price">
                                    <span>total</span>
                                    <span className="price">{this.get_total_price()}$</span>
                                </div>
                            </div>
                            <div className="modal-footer">
                                <div className="finalize-the-buy-list">
                                    <button data-dismiss="modal">Close</button>
                                    <a href={"user?username=" + this.props.username + "&pay=1"}><button>Buy!</button></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}