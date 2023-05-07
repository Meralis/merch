import { useRouteError } from "react-router-dom";

 function ErrorPage() {
    const error = useRouteError();

    return (
        <div id="error-page">
            <h2>Sorry, an unexpected error has occurred.</h2>
            <p>
                <i>{error.statusText || error.message}</i>
            </p>
        </div>
    );
}
export  default ErrorPage;