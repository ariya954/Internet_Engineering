class PurchasedListItem extends React.Component {
    constructor(props) {
        super(props);
        this.state = ({ purchased_list_item_name: "",
            purchased_list_item_provider_id: 0,
            purchased_list_item_categories: [],
            purchased_list_item_inStock: 0,
            purchased_list_item_price:   0,
            purchased_list_item_image:  "",
            purchased_list_item_rate: 0.0,});
    }

    fetch_purchased_list_item_info_from_API() {
        const response = fetch('/getCommodityInfo?id=' + this.props.purchased_list_item_id)
            .then(response => response.json())
            .then(response => this.setState(prevState => ({ purchased_list_item_name : response._name,
                purchased_list_item_provider_id: response._provider_id,
                purchased_list_item_categories: response._categories,
                purchased_list_item_inStock: response._inStock,
                purchased_list_item_price: response._price,
                purchased_list_item_image: response._image,
                purchased_list_item_rate: response._rating})));
    }

    componentDidMount() {
        this.fetch_purchased_list_item_info_from_API();
    }

    purchased_list_item_categories() {
        const categories = this.state.purchased_list_item_categories.at(0);
        return <div>{categories}</div>;
    }

    render() {
        const purchased_list_item_name = this.state.purchased_list_item_name;
        const purchased_list_item_image = this.state.purchased_list_item_image.toString();
        const purchased_list_item_provider_id = this.state.purchased_list_item_provider_id;
        const purchased_list_item_inStock = this.state.purchased_list_item_inStock;
        const purchased_list_item_price = this.state.purchased_list_item_price;
        const purchased_list_item_rate = this.state.purchased_list_item_rate;
        return (
            <table>
                <tr>
                    <td><img src={purchased_list_item_image}/></td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
                    <td className="gray">{purchased_list_item_name}</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
                    <td className="gray">{this.purchased_list_item_categories()}</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
                    <td className="gray">${purchased_list_item_price}</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
                    <td className="gray">{purchased_list_item_provider_id}</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
                    <td className="yellow">{purchased_list_item_rate}</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
                    <td className="red">{purchased_list_item_inStock}</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
                    <td className="gray">{this.props.number_of_purchased}</td><td>&nbsp;</td><td>&nbsp;</td>
                </tr>
            </table>
        );
    }
}