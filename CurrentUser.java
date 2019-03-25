public class CurrentUser{
  UserClass user;
  public CurrentUser(UserClass us){
    user = us;
  }
  public void setCurrentUser(UserClass us){
    user = us;
  }
  public UserClass getCurrentUser(){
    return user;
  }
}
