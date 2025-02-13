
// tell what each action will do
export const increase = (num) => {
    return {// key-value
        type: 'increase',
        value: num
    }
}
export const decrease = (num) => {
     return {
        type: 'decrease',
        value: num
    }
}
export const setToZero = () => {
     return {
        type: 'settozero',
        value: 0
    }
}
export const divide = (num) => {
      return {
        type: 'divide',
        value: num
    }
}
export const multiplay = (num) => {
      return {
        type: 'multiplay',
        value: num
    }
}

export const addNewUser = (newUser) => {
      return {
        type: 'addingUser',
        value: newUser
    }
}

export const deleteNewUser = (todeleteID) => {
      return {
        type: 'deletingUser',
        value: todeleteID
    }
}
