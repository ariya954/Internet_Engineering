class Card extends React.Component {
    constructor(props) {
        super(props);
        this.state = { commodity_name: "" ,
                       commodity_image: "",
                       commodity_inStock: 0,
                       commodity_price:   0,
                       commodity_id: 0};
    }

    fetch_commodity_info_from_API() {
        const response = fetch('/getCommodityInfo?id=' + this.props.commodity_id)
            .then(response => response.json())
            .then(response => this.setState(prevState => ({ commodity_name : response._name,
                                                        commodity_inStock: response._inStock,
                                                        commodity_price: response._price,
                                                        commodity_image: response._image,
                                                        commodity_id: response._id})));
    }

    componentDidMount() {
        this.fetch_commodity_info_from_API();
    }

    render() {
        const commodity_name = this.state.commodity_name.length < 19 ? this.state.commodity_name :
                               this.state.commodity_name.slice(this.state.commodity_name.length - 20, this.state.commodity_name.length);
        const commodity_price = this.state.commodity_price;
        const commodity_image = this.state.commodity_image.toString();
        const commodity_inStock = this.state.commodity_inStock;
        return (
            <div className="card">
                <label className="commodity">{commodity_name}</label>
                <p className="in-stock">{commodity_inStock} left in stock</p>
                <img src={commodity_image}/>
                <p className="commodity-price">{commodity_price}$</p>
                <div className="add-to-cart">
                    <a href={"commodity?id=" + this.state.commodity_id + "&username=" + this.props.current_logged_in_username}><button>add to cart</button></a>
                </div>
            </div>
        );
    }
}