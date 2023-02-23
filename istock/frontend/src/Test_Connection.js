function Test_Connection() {
    fetch('http://localhost:8080/').then(data => data.json()).then(data => {
        return <h1>data</h1>;
    })
}

export default Test_Connection;