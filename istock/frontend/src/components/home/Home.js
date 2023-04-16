import SelectCategories from "../product/SelectCategories";

function Home() {

    return <>
        <div className={'main-photo'}>
            <img src={"/images/dog-cat-1.png"} alt={"animals"} width={"850"} className={'img-fluid'}/>
        </div>
        <div className={'main-nav'}>
            <SelectCategories/>
        </div>
    </>
}

export default Home;