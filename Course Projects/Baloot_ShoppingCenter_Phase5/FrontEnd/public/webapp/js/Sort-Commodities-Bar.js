class SortCommoditiesBar extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="sort-commodities-bar">
                <p><b>Available commodities&nbsp;&nbsp;</b></p>
                <label className="switch">
                    <input type="checkbox" />
                    <span className="slider round"></span>
                </label>
                <div className="button-group">
                    <p>sort by:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href={"commodities?page=" + this.props.number_of_current_page + "&username=" + this.props.current_logged_in_username + "&sort_by_name=1"}><button>name</button></a>
                        <a href={"commodities?page=" + this.props.number_of_current_page + "&username=" + this.props.current_logged_in_username + "&sort_by_price=1"}><button>price</button></a>
                    </p>
                </div>
            </div>
        );
    }
}