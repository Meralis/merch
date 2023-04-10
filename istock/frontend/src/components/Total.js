function Total({products}) {
    return (
        <div className={'total color-light-theme'}>
            <b>До сплати: {products.reduce((total, product) => total + (product.count * product.price), 0).toFixed(2)} грн</b>
        </div>
    );
}

export default Total;