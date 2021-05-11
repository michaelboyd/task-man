import {User} from "./user.model";

export class Task {
    id: number;
    name: string;
    status: string;
    user: User;
    createDate: Date;
    updateDate: Date;
}
