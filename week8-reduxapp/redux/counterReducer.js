

export default function counterReducer(state = 0, action) { 
    // if action is adding ===> state ==> state + 1
    // if action is sub ===> state ===> state - 1
    switch (action.type) { 
        case 'increase': 
             return state + action.value;
        case 'decrease': 
             return state - action.value;
        case 'divide':
            return state / action.value;
        case 'multiplay':
            return state * action.value;
        case 'settozero':
            return (state = 0) ;
        
        default: return state;
    }


}