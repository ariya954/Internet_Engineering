$(document).ready(function() {
    $("#star1").click(function() {
        $(".product-rate .fa-star").css("color", "#A8A8A8");
        $("#star1").css("color", "orange");
    });
    $("#star2").click(function() {
        $(".product-rate .fa-star").css("color", "#A8A8A8");
        $("#star1, #star2").css("color", "orange");
    });
    $("#star3").click(function() {
        $(".product-rate .fa-star").css("color", "#A8A8A8")
        $("#star1, #star2, #star3").css("color", "orange");
    });
    $("#star4").click(function() {
        $(".product-rate .fa-star").css("color", "#A8A8A8");
        $("#star1, #star2, #star3, #star4").css("color", "orange");
    });
    $("#star5").click(function() {
        $(".product-rate .fa-star").css("color", "#A8A8A8");
        $("#star1, #star2, #star3, #star4, #star5").css("color", "orange");
    });
    $("#star6").click(function() {
        $(".product-rate .fa-star").css("color", "#A8A8A8");
        $("#star1, #star2, #star3, #star4, #star5, #star6").css("color", "orange");
    });
    $("#star7").click(function() {
        $(".product-rate .fa-star").css("color", "#A8A8A8");
        $("#star1, #star2, #star3, #star4, #star5, #star6, #star7").css("color", "orange");
    });
    $("#star8").click(function() {
        $(".product-rate .fa-star").css("color", "#A8A8A8")
        $("#star1, #star2, #star3, #star4, #star5, #star6, #star7, #star8").css("color", "orange");
    });
    $("#star9").click(function() {
        $(".product-rate .fa-star").css("color", "#A8A8A8");
        $("#star1, #star2, #star3, #star4, #star5, #star6, #star7, #star8, #star9").css("color", "orange");
    });
    $("#star10").click(function() {
        $(".product-rate .fa-star").css("color", "#A8A8A8");
        $("#star1, #star2, #star3, #star4, #star5, #star6, #star7, #star8, #star9, #star10").css("color", "orange");
    });
});

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
        var given_rate = 10;
        if(document.getElementById("star10").style.color === "orange")
            given_rate = 10;
        else if(document.getElementById("star9").style.color === "orange")
            given_rate = 9;
        else if(document.getElementById("star8").style.color === "orange")
            given_rate = 8;
        else if(document.getElementById("star7").style.color === "orange")
            given_rate = 7;
        else if(document.getElementById("star6").style.color === "orange")
            given_rate = 6;
        else if(document.getElementById("star5").style.color === "orange")
            given_rate = 5;
        else if(document.getElementById("star4").style.color === "orange")
            given_rate = 4;
        else if(document.getElementById("star3").style.color === "orange")
            given_rate = 3;
        else if(document.getElementById("star2").style.color === "orange")
            given_rate = 2;
        else if(document.getElementById("star1").style.color === "orange")
            given_rate = 1;
        window.location.href = "commodity?id=" + this.props.product_id + "&username=" +  this.props.username + "&commodity_rate=" + given_rate;
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
                    <span className="fa fa-star checked" aria-hidden = "true" id = "star1"></span>
                    <span className="fa fa-star checked" aria-hidden = "true" id = "star2"></span>
                    <span className="fa fa-star checked" aria-hidden = "true" id = "star3"></span>
                    <span className="fa fa-star checked" aria-hidden = "true" id = "star4"></span>
                    <span className="fa fa-star checked" aria-hidden = "true" id = "star5"></span>
                    <span className="fa fa-star checked" aria-hidden = "true" id = "star6"></span>
                    <span className="fa fa-star checked" aria-hidden = "true" id = "star7"></span>
                    <span className="fa fa-star checked" aria-hidden = "true" id = "star8"></span>
                    <span className="fa fa-star checked" aria-hidden = "true" id = "star9"></span>
                    <span className="fa fa-star checked" aria-hidden = "true" id = "star10"></span>
                    <div className="submit">
                        <button id="SubmitButton" onClick={this.submit_rate_event.bind(this)}>submit</button>
                    </div>
                </div>
                <div className="product-rating">
                    <span className="fa fa-star checked"></span>
                    <p>{product_rate}</p><p className="gray">({this.props.number_of_rates})</p>
                </div>
            </div>
        );
    }
}