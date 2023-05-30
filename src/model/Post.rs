use serde::{Serialize, Deserialize, JsonSchema};

#[derive(Serialize, Deserialize, JsonSchema)]
pub struct Post {
    id: String,
    user_id: String,
    heading: String,
    body: String,
    creation_date: String,
    last_updated: String,
}