import { legacy_createStore as createStore } from "redux";
import counterReducer from "./counterReducer";


const counterStore = createStore(counterReducer)
export default counterStore;