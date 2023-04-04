import SelectCategories from "../product/SelectCategories";

function Home() {

    return <>
        <div className={'main-photo'}>
            <img src={"/images/dog-cat-1.png"} alt={"animals"}/>
        </div>
        <div className={'main-nav'}>
            <SelectCategories/>
        </div>
    </>
}

export default Home;