class ProductInfo extends React.Component {
    constructor(props) {
        super(props);
        this.state = ({ product_name: "",
            product_categories: [],
            product_provider_id: 0,
            product_inStock: 0,
            product_price:   0,
            product_image:  "",
            product_id:      0,
            product_rate: 0.0,});
    }

    fetch_product_info_from_API() {
        const response = fetch('/getCommodityInfo?id=' + this.props.product_id)
            .then(response => response.json())
            .then(response => this.setState(prevState => ({ product_name : response._name,
                product_provider_id: response._provider_id,
                product_categories: response._categories,
                product_inStock: response._inStock,
                product_price: response._price,
                product_image: response._image,
                product_rate: response._rating,
                product_id: response._id})));
    }

    componentDidMount() {
        this.fetch_product_info_from_API();
    }

    submit_rate_event() {
        const submit_button = document.getElementById("SubmitButton");
        submit_button.innerHTML = "loading";
        submit_button.style.color = "white";
        submit_button.style.background = "#909090";
    }

    product_categories() {
        const categories = this.state.product_categories.map((category, idx) =>
            <li key={idx}>
                {category}
            </li>
        );
        return <div>{categories}</div>;
    }

    render() {
        const product_name = this.state.product_name.length < 19 ? this.state.product_name :
            this.state.product_name.slice(this.state.product_name.length - 20, this.state.product_name.length);
        const product_image = this.state.product_image.toString();
        const product_provider_id= this.state.product_provider_id;
        const product_inStock = this.state.product_inStock;
        const product_price = this.state.product_price;
        const product_rate = this.state.product_rate;
        return (
            <div className="product-info">
                <img src={product_image} />
                <label className="product-name">{product_name}</label>
                <p className="product-in-stock">{product_inStock} left in stock</p>
                <div className="product-provider">
                    <p>by&nbsp;<a href={"provider?id=" + product_provider_id + "&username=" + this.props.username}><label>{this.props.product_provider}</label></a></p>
                </div>
                <p className="product-category">Category(s)</p>
                <ul className="product-category">
                    {this.product_categories()}
                </ul>
                <div className="product-price">
                    <p>{product_price}$</p>
                    {this.props.number_of_this_commodity_in_cart.toString() === "0" &&
                        <div className="add-to-cart">
                            <a href={"commodity?id=" + this.state.product_id + "&username=" + this.props.username + "&add_to_cart=1"}><button>add to cart</button></a>
                        </div>
                    }
                    {this.props.number_of_this_commodity_in_cart.toString() !== "0" &&
                        <div className="stepper-input">
                            <input type="range" min="1" max="100" value="1"/>
                                <div className="input">
                                    <a href={"commodity?id=" + this.state.product_id + "&username=" + this.props.username + "&remove_from_cart=1"}><button className="minus-button">-</button></a>
                                    <div className="range">
                                        <div className="list"><span>{this.props.number_of_this_commodity_in_cart}</span></div>
                                    </div>
                                    <a href={"commodity?id=" + this.state.product_id + "&username=" + this.props.username + "&add_to_cart=1"}><button className="plus-button">+</button></a>
                                </div>
                        </div>
                    }
                </div>
                <div className="product-rate">
                    <p>rate now</p>
                    <span className="fa fa-star checked"></span>
                    <span className="fa fa-star checked"></span>
                    <span className="fa fa-star checked"></span>
                    <span className="fa fa-star checked"></span>
                    <span className="fa fa-star checked"></span>
                    <span className="fa fa-star checked"></span>
                    <span className="fa fa-star checked"></span>
                    <span className="fa fa-star checked"></span>
                    <span className="fa fa-star checked"></span>
                    <span className="fa fa-star checked"></span>
                    <div className="submit">
                        <button id="SubmitButton" onClick={this.submit_rate_event}>submit</button>
                    </div>
                </div>
                <div className="product-rating">
                    <span className="fa fa-star checked"></span>
                    <p>{product_rate}</p><p className="gray">(12)</p>
                </div>
            </div>
        );
    }
}