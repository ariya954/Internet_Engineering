class BuyListItem extends React.Component {
    constructor(props) {
        super(props);
        this.state = ({ buy_list_item_name: "",
            buy_list_item_provider_id: 0,
            buy_list_item_categories: [],
            buy_list_item_inStock: 0,
            buy_list_item_price:   0,
            buy_list_item_image:  "",
            buy_list_item_rate: 0.0,});
    }

    fetch_buy_list_item_info_from_API() {
        const response = fetch('/getCommodityInfo?id=' + this.props.buy_list_item_id)
            .then(response => response.json())
            .then(response => this.setState(prevState => ({ buy_list_item_name : response._name,
                buy_list_item_provider_id: response._provider_id,
                buy_list_item_categories: response._categories,
                buy_list_item_inStock: response._inStock,
                buy_list_item_price: response._price,
                buy_list_item_image: response._image,
                buy_list_item_rate: response._rating})));
    }

    componentDidMount() {
        this.fetch_buy_list_item_info_from_API();
    }

    buy_list_item_categories() {
        const categories = this.state.buy_list_item_categories.at(0);
        return <div>{categories}</div>;
    }

    render() {
        const buy_list_item_name = this.state.buy_list_item_name;
        const buy_list_item_image = this.state.buy_list_item_image.toString();
        const buy_list_item_provider_id = this.state.buy_list_item_provider_id;
        const buy_list_item_inStock = this.state.buy_list_item_inStock;
        const buy_list_item_price = this.state.buy_list_item_price;
        const buy_list_item_rate = this.state.buy_list_item_rate;
        return (
            <table>
                <tr>
                    <td><img src={buy_list_item_image}/></td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
                    <td className="gray">{buy_list_item_name}</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
                    <td className="gray">{this.buy_list_item_categories()}</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
                    <td className="gray">${buy_list_item_price}</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
                    <td className="gray">{buy_list_item_provider_id}</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
                    <td className="yellow">{buy_list_item_rate}</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
                    <td className="red">{buy_list_item_inStock}</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
                    <td>
                        <div className="stepper-input">
                            <input type="range" min="1" max="100" value="1"/>
                            <div className="input">
                                <a href={"user?username=" + this.props.current_logged_in_user + "&commodity_id=" + this.props.buy_list_item_id + "&remove_from_cart=1"}><button className="minus-button">-</button></a>
                                <div className="range">
                                    <div className="list"><span>{this.props.number_of_buy_list_item_in_cart}</span></div>
                                </div>
                                <a href={"user?username=" + this.props.current_logged_in_user + "&commodity_id=" + this.props.buy_list_item_id + "&add_to_cart=1"}><button className="plus-button">+</button></a>
                            </div>
                        </div>
                    </td>
                </tr>
            </table>
        );
    }
}