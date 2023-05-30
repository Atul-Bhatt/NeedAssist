use serde::{Serialize, Deserialize, JsonSchema};

#[derive(Serialize, Deserialize, JsonSchema)]
pub struct Comment {
    id: String,
    text: String,
    user_id: String,
}
