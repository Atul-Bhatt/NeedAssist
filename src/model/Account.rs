use serde::{Serialize, Deserialize, JsonSchema};

#[derive(Serialize, Deserialize, JsonSchema)]
pub struct Account {
    id: String,
    password: String,
}