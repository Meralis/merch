function changeCount(products, productId, newCount) {
    return products.map(product => ({
        ...product,
        count: productId === product.productId ? newCount : product.count
    }));
}

export default changeCount;