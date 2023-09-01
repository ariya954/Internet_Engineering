class Pagination extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="pagination">
                <a href={"commodities?page=" + (this.props.number_of_current_page > 1 ? this.props.number_of_current_page - 1 : 1) + " &username=" + this.props.current_logged_in_username}>&laquo;</a>
                <a href={"commodities?page=" + (this.props.number_of_current_page <= 5 ? "1" : (this.props.number_of_current_page - 4)) + " &username=" + this.props.current_logged_in_username}>{(this.props.number_of_current_page <= 5 ? 1 : this.props.number_of_current_page - 4)}</a>
                <a href={"commodities?page=" + (this.props.number_of_current_page <= 5 ? "2" : (this.props.number_of_current_page - 3)) + " &username=" + this.props.current_logged_in_username}>{(this.props.number_of_current_page <= 5 ? 2 : this.props.number_of_current_page - 3)}</a>
                <a href={"commodities?page=" + (this.props.number_of_current_page <= 5 ? "3" : (this.props.number_of_current_page - 2)) + " &username=" + this.props.current_logged_in_username}>{(this.props.number_of_current_page <= 5 ? 3 : this.props.number_of_current_page - 2)}</a>
                <a href={"commodities?page=" + (this.props.number_of_current_page <= 5 ? "4" : (this.props.number_of_current_page - 1)) + " &username=" + this.props.current_logged_in_username}>{(this.props.number_of_current_page <= 5 ? 4 : this.props.number_of_current_page - 1)}</a>
                <a href={"commodities?page=" + (this.props.number_of_current_page <= 5 ? "5" : (this.props.number_of_current_page)) + " &username=" + this.props.current_logged_in_username}>{(this.props.number_of_current_page <= 5 ? 5 : this.props.number_of_current_page)}</a>
                <a href="#">...</a>
                <a href={"commodities?page=" + (this.props.number_of_end_page <= 10 ? "10" : (this.props.number_of_end_page)) + " &username=" + this.props.current_logged_in_username}>{(this.props.number_of_end_page <= 10 ? "10" : (this.props.number_of_end_page))}</a>
                <a href={"commodities?page=" + (this.props.number_of_current_page < this.props.number_of_end_page ? parseInt(this.props.number_of_current_page) + 1 : this.props.number_of_end_page > 10 ? this.props.number_of_end_page : 10) + " &username=" + this.props.current_logged_in_username}>&raquo;</a>
            </div>
        );
    }
}