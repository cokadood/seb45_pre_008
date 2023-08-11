import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App.jsx';
import GlobalStyle from './assets/styles/GlobalStyle.jsx';
import { BrowserRouter } from 'react-router-dom';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <BrowserRouter>
    <GlobalStyle />
    <App />
  </BrowserRouter>,
);
