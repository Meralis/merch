function Footer() {
    return <>
        <div className={'footer row d-flex justify-content-around color-dark-theme'}>
            <div>
                <div className="d-flex flex-column">
                    <span className="h6"><b>Графік роботи:</b></span>
                    <a>ПН - ПТ: 09:00 - 20:00<br/>СБ: 09:00 - 18:00<br/>НД: 09:00 - 18:00 </a>
                </div>
            </div>
            <div>
                <div><b>Контакти:</b></div>
                <div className="d-flex flex-column">
                    <a href="tel:0800000000" className="footer_tel">0 800 00 00 00</a>
                    <a href="mailto:melissa001@ukr.net">melissa001@ukr.net</a>
                    <div><b>Адреса:</b></div>
                    <span className="h6">Київ. вул. Степана Бандери, 18</span>
                </div>
            </div>
        </div>
    </>
}

export default Footer;