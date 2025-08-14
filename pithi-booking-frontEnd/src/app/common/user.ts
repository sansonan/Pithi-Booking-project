export class User {
  constructor(
    public id?: string,
    public username?: string,
    public email?: string,
    public password?: string,
    public confirmPassword?: string,
    public fullName?: string,
    public phone?: string,
    public profileImageUrl?: string,
    public address?: string,
    public createdAt?: string,            // Use string for ISO date or Date if you prefer
    public lastModifiedAt?: string,       // Use string for ISO date or Date if you prefer
    public accountNonExpired?: boolean,
    public accountNonLocked?: boolean,
    public credentialsNonExpired?: boolean,
    public enabled?: boolean,
    public role?: string
  ) {}
}

