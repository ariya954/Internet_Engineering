class ProviderInfo extends React.Component {
    constructor(props) {
        super(props);
        this.state = { provider_name: "" };
    }

    fetch_provider_info_from_API() {
        const response = fetch('/getProviderInfo?id=' + this.props.provider_id)
            .then(response => response.json())
            .then(response => this.setState(prevState => ({ provider_name : response._name })));
    }

    componentDidMount() {
        this.fetch_provider_info_from_API();
    }

    render() {
        const provider_name = this.state.provider_name;
        return (
            <div className="provider_logo">
                <img src="photo/Huawei.png"/>
                <div className="provider_info">
                    <div className="provider_name">{provider_name}</div>
                    <div className="provider_logo_text">since 1990</div>
                </div>
            </div>
        );
    }
}