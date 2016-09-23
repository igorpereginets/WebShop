<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row content">
	<div class="col-md-2">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">My Account</h3>
			</div>
			<div class="panel-body">
				<ul class="list-group">
					<li class="list-group-item"><a href="index.html" class="account-info"> My orders </a></li>
					<li class="list-group-item"><a href="index.html" class="account-info"> My personal info </a></li>
					<li class="list-group-item"><a href="index.html" class="account-info"> My wishlist </a></li>
				</ul>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Categories</h3>
			</div>
			<div class="panel-body">
				<ul class="list-group">
					<c:forEach items="${categories}" var="category">
						<li class="list-group-item"><a href="/" class="category"> ${category.name} <span class="badge">${category.count}</span>
						</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Brands</h3>
			</div>
			<div class="panel-body">
				<ul class="list-group">
					<c:forEach items="${producers}" var="producer">
						<li class="list-group-item"><a href="/" class="brands"> ${producer.name} </a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	<div class="col-md-8">
		<div class="row#!/usr/bin/env ">
			<div class="container-fluid">
				<div class="content-wrapper">
					<div class="item-container">
						<div class="container">
							<div class="col-md-7">
								<div class="thumbnail">
									<a href="${good.pathToFile}" data-lightbox="image-1" data-title="${good.name}"> <img id="item-display" src="${good.pathToFile}"
										alt="${good.name}"></img></a>
								</div>
							</div>
							<div class="col-md-5">
								<div class="product-title">${good.name}</div>
								<div class="product-desc">${good.shortDescription}</div>
								<div class="product-rating">
									<i class="fa fa-star gold"></i> <i class="fa fa-star gold"></i> <i class="fa fa-star gold"></i> <i class="fa fa-star gold"></i> <i
										class="fa fa-star-o"></i>
								</div>
								<hr>
								<div class="product-price">${good.price}$</div>
								<div class="product-stock">${good.state.name}</div>
								<hr>
								<div class="btn-group cart">
									<button type="button" class="btn btn-success">Add to cart</button>
								</div>
								<div class="btn-group wishlist">
									<button type="button" class="btn btn-danger">Add to wishlist</button>
								</div>
							</div>
						</div>
					</div>
					<div class="container-fluid">
						<div class="col-md-12 product-info">
							<ul id="myTab" class="nav nav-tabs nav_tabs">

								<li class="active"><a href="#service-one" data-toggle="tab">DESCRIPTION</a></li>
								<li><a href="#service-two" data-toggle="tab">PRODUCT INFO</a></li>
								<li><a href="#service-three" data-toggle="tab">REVIEWS</a></li>

							</ul>
							<div id="myTabContent" class="tab-content">
								<div class="tab-pane fade in active" id="service-one">

									<section class="container product-info">${good.description}</section>

								</div>
								<div class="tab-pane fade" id="service-two">

									<section class="container"></section>

								</div>
								<div class="tab-pane fade" id="service-three"></div>
							</div>
							<hr>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-2">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">My Bucket</h3>
			</div>
			<div class="panel-body">
				<h5>No Products...</h5>
				<button type="button" class="btn btn-primary navbar-btn">Check Out</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade contact" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
	<div class="modal-dialog modal-sm" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<div class="modal-title">
					<h4 class="modal-title" id="myModalLabel">Contact Us</h4>
				</div>
			</div>
			<p class="contact-us">
				Made by Igor Pereginets><br />E-mail: example@exam.ua<br />Skype: skypeLogin<br />Country: Ukraine<br />City: Lviv<br />Street: ....
			</p>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade help" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<div class="modal-title">
					<h4 class="modal-title" id="myModalLabel">Help</h4>
				</div>
			</div>
			<p class="help-me">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Praesentium alias asperiores eos voluptatibus fuga sed vitae
				neque, mollitia, iusto velit et fugiat quibusdam voluptas ratione numquam quis nulla earum quo. Obcaecati nemo, sed quod debitis repellendus.
				Placeat officia, nesciunt illo. Tenetur adipisci possimus architecto reprehenderit saepe, aut consequatur delectus officiis distinctio iste quos
				necessitatibus eum excepturi, perspiciatis laboriosam quas. Modi dolores, aut tempora saepe voluptatibus. Pariatur ipsum consectetur voluptatum
				quod earum delectus fugiat reiciendis? Odit nemo tenetur, consequatur iste, soluta cumque earum voluptates deleniti perspiciatis aperiam
				distinctio, quae consectetur. Earum hic aperiam atque consectetur dolore quibusdam quasi id expedita explicabo, iste, facere aut error molestiae
				debitis illum fugit nostrum recusandae animi, veritatis ipsam eum. Obcaecati magni eum, minima debitis vel omnis corporis, eaque tempora optio
				eius earum quod nihil dolor fugiat doloremque harum eveniet voluptas, praesentium, sunt consequatur. Debitis architecto dolor aut harum, tenetur
				quidem delectus suscipit itaque natus iste pariatur saepe cupiditate? Sunt aliquid vero sit maiores molestias praesentium velit eligendi
				recusandae voluptate iure odit consequatur ea consequuntur, quos debitis ullam a nisi, voluptatibus inventore reprehenderit reiciendis labore, id.
				At, quas harum voluptate iste, aliquid soluta ab iure tenetur, dicta enim ratione reiciendis itaque dolore adipisci porro aliquam quam quia?
				Dolor, repudiandae, impedit, illo ea quae deserunt iste quas commodi reprehenderit sapiente tenetur consequuntur? Aperiam deleniti dolores dolor
				ipsum deserunt repudiandae doloremque, consequatur voluptates vero eligendi accusantium. In voluptatibus laboriosam ullam ducimus, officia. Saepe
				corrupti enim quas debitis totam similique assumenda, ullam doloremque doloribus, voluptatum deleniti odit autem libero! Quod quasi error a quo
				architecto ratione recusandae veniam dicta, corporis adipisci vel quae fugiat quibusdam illum reprehenderit, assumenda reiciendis alias,
				perferendis possimus! Odit veniam autem sapiente, inventore adipisci maiores voluptatum, molestiae possimus aliquid quia nostrum, sint distinctio
				facilis dolorum nisi! Blanditiis aperiam quo quis enim omnis praesentium, fugit explicabo, earum! Voluptatibus aperiam provident quisquam, dolor,
				totam beatae laboriosam inventore et sapiente. Architecto dicta facere modi suscipit impedit, repudiandae eligendi dignissimos numquam,
				consequuntur quia illo fugiat officia. Recusandae aspernatur ut dignissimos perferendis temporibus quo id? Quam blanditiis fugiat, officia eius
				consectetur accusantium aliquid, beatae vitae ipsum cum deserunt modi perferendis. Iure numquam incidunt atque quis accusantium aliquid, quia
				consectetur ipsa corporis voluptates doloremque enim nesciunt sequi. Quis blanditiis, debitis delectus incidunt laborum nihil facere at impedit
				explicabo, doloribus asperiores itaque, eaque consectetur sit saepe illum provident non vero! Architecto dignissimos voluptas, aperiam doloremque
				officia blanditiis optio, perspiciatis tempora vitae officiis, facilis quas cupiditate possimus, animi! Repudiandae labore minus sequi iusto ipsam
				consequatur nisi praesentium harum vero alias facere tenetur tempore reprehenderit perspiciatis quas voluptate porro earum dolores voluptatum
				voluptates veniam, pariatur quis quisquam hic. Ratione quidem obcaecati amet praesentium iure laudantium nihil ad distinctio totam a dicta sint et
				perferendis eligendi officiis iste, quis, culpa. Temporibus dolorum culpa cum, quis a ratione neque totam tempora blanditiis sint quia labore,
				distinctio voluptate debitis in odio, quas deserunt laudantium. Itaque laborum, quis nesciunt modi nobis ducimus cum corporis, quam, rem illum
				ipsum, eum! Commodi iure, reiciendis ex dolor impedit dolorem voluptas atque.</p>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade about" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<div class="modal-title">
					<h4 class="modal-title" id="myModalLabel">About</h4>
				</div>
			</div>
			<p class="about-us">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Praesentium alias asperiores eos voluptatibus fuga sed vitae
				neque, mollitia, iusto velit et fugiat quibusdam voluptas ratione numquam quis nulla earum quo. Obcaecati nemo, sed quod debitis repellendus.
				Placeat officia, nesciunt illo. Tenetur adipisci possimus architecto reprehenderit saepe, aut consequatur delectus officiis distinctio iste quos
				necessitatibus eum excepturi, perspiciatis laboriosam quas. Modi dolores, aut tempora saepe voluptatibus. Pariatur ipsum consectetur voluptatum
				quod earum delectus fugiat reiciendis? Odit nemo tenetur, consequatur iste, soluta cumque earum voluptates deleniti perspiciatis aperiam
				distinctio, quae consectetur. Earum hic aperiam atque consectetur dolore quibusdam quasi id expedita explicabo, iste, facere aut error molestiae
				debitis illum fugit nostrum recusandae animi, veritatis ipsam eum. Obcaecati magni eum, minima debitis vel omnis corporis, eaque tempora optio
				eius earum quod nihil dolor fugiat doloremque harum eveniet voluptas, praesentium, sunt consequatur. Debitis architecto dolor aut harum, tenetur
				quidem delectus suscipit itaque natus iste pariatur saepe cupiditate? Sunt aliquid vero sit maiores molestias praesentium velit eligendi
				recusandae voluptate iure odit consequatur ea consequuntur, quos debitis ullam a nisi, voluptatibus inventore reprehenderit reiciendis labore, id.
				At, quas harum voluptate iste, aliquid soluta ab iure tenetur, dicta enim ratione reiciendis itaque dolore adipisci porro aliquam quam quia?
				Dolor, repudiandae, impedit, illo ea quae deserunt iste quas commodi reprehenderit sapiente tenetur consequuntur? Aperiam deleniti dolores dolor
				ipsum deserunt repudiandae doloremque, consequatur voluptates vero eligendi accusantium. In voluptatibus laboriosam ullam ducimus, officia. Saepe
				corrupti enim quas debitis totam similique assumenda, ullam doloremque doloribus, voluptatum deleniti odit autem libero! Quod quasi error a quo
				architecto ratione recusandae veniam dicta, corporis adipisci vel quae fugiat quibusdam illum reprehenderit, assumenda reiciendis alias,
				perferendis possimus! Odit veniam autem sapiente, inventore adipisci maiores voluptatum, molestiae possimus aliquid quia nostrum, sint distinctio
				facilis dolorum nisi! Blanditiis aperiam quo quis enim omnis praesentium, fugit explicabo, earum! Voluptatibus aperiam provident quisquam, dolor,
				totam beatae laboriosam inventore et sapiente. Architecto dicta facere modi suscipit impedit, repudiandae eligendi dignissimos numquam,
				consequuntur quia illo fugiat officia. Recusandae aspernatur ut dignissimos perferendis temporibus quo id? Quam blanditiis fugiat, officia eius
				consectetur accusantium aliquid, beatae vitae ipsum cum deserunt modi perferendis. Iure numquam incidunt atque quis accusantium aliquid, quia
				consectetur ipsa corporis voluptates doloremque enim nesciunt sequi. Quis blanditiis, debitis delectus incidunt laborum nihil facere at impedit
				explicabo, doloribus asperiores itaque, eaque consectetur sit saepe illum provident non vero! Architecto dignissimos voluptas, aperiam doloremque
				officia blanditiis optio, perspiciatis tempora vitae officiis, facilis quas cupiditate possimus, animi! Repudiandae labore minus sequi iusto ipsam
				consequatur nisi praesentium harum vero alias facere tenetur tempore reprehenderit perspiciatis quas voluptate porro earum dolores voluptatum
				voluptates veniam, pariatur quis quisquam hic. Ratione quidem obcaecati amet praesentium iure laudantium nihil ad distinctio totam a dicta sint et
				perferendis eligendi officiis iste, quis, culpa. Temporibus dolorum culpa cum, quis a ratione neque totam tempora blanditiis sint quia labore,
				distinctio voluptate debitis in odio, quas deserunt laudantium. Itaque laborum, quis nesciunt modi nobis ducimus cum corporis, quam, rem illum
				ipsum, eum! Commodi iure, reiciendis ex dolor impedit dolorem voluptas atque.</p>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>