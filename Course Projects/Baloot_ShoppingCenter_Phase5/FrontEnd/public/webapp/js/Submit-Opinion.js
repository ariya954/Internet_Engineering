class SubmitOpinion extends React.Component {
    constructor(props) {
        super(props);
    }

    post_comment_event() {
        window.location.href = "commodity?id=" + this.props.product_id + "&username=" +  this.props.username + "&comment=" + document.getElementById("opinion").value;
    }

    render() {
        return (
            <div className="submit-opinion">
                <label className="heading">Submit your opinion</label>
                <input type="text" id="opinion"/>
                <button id="PostButton" onClick={this.post_comment_event.bind(this)}>Post</button>
            </div>
        );
    }
}