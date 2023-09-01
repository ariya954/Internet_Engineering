class UserHeader extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="user-header">
                <img src="photo/Baloot Logo.png" />
                <p>{this.props.username}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                <div className="btn-group">
                    <button>Cart&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{this.props.number_of_commodities_in_cart}</button>
                </div>
            </div>
        );
    }
}