use serde::{Serialize, Deserialize, JsonSchema};

#[derive(Serialize, Deserialize, JsonSchema)]
pub struct User {
    id: String,
    username: String,
    password: String,
    roles: String,
}