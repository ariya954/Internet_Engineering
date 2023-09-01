class Comment extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div>
                <li>
                    <label className="black">{this.props.comment}</label>
                    <br />
                    <br />
                    <label className="comment-info">{this.props.comment_date}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{this.props.user_email}</label>
                    <label className="rate-comment">Is this comment helpful?</label>
                    <label className="like-dislike-comment">&nbsp;&nbsp;{this.props.number_of_likes}&nbsp;&nbsp;
                        <a href={"commodity?id=" + this.props.commodity_id + "&username=" + this.props.current_logged_in_username + "&comment_rate=1&comment_id=" + this.props.comment_id}><i className="bi bi-hand-thumbs-up"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;{this.props.number_of_dislikes}&nbsp;&nbsp;
                        <a href={"commodity?id=" + this.props.commodity_id + "&username=" + this.props.current_logged_in_username + "&comment_rate=-1&comment_id=" + this.props.comment_id}><i className="bi bi-hand-thumbs-down"></i></a>
                    </label>
                </li>
            </div>
        );
    }
}