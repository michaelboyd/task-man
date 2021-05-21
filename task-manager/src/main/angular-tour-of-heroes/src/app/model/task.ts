import { User } from './user'

export interface Task {
    id: number;
    name: string;
    status: string;
    user: User;
    createDate: Date;
    updateDate: Date;
}