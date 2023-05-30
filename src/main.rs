mod api;

use crate::api::health_check::health;

use actix_web::{HttpServer, App, middleware::Logger};

#[actix_web::main]
async fn main() -> std::io::Result<()> {
	std::env::set_var("RUST_LOG", "debug");
	std::env::set_var("RUST_BACKTRACE", "1");
	env_logger::init();

	HttpServer::new(move || {
		let logger = Logger::default();
		App::new()
		.wrap(logger)
		.service(health)
	})
	.bind(("127.0.0.1", 8000))?
	.run()
	.await
}