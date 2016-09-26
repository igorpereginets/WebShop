<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<div class="row">
	<div class="col-md-12">
		<nav class="navbar navbar-default navbar-fixed-top">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/"><img src="/resources/img/logo2.png" height="40" alt="Brand"></a>

			</div>
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active"><a href="/"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>Home</a></li>
					<security:authorize access="isAuthenticated()">
						<li><a href="/createGoods"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>Add Goods</a></li>
					</security:authorize>
					<li><a href="#" role="button" data-toggle="modal" data-target=".about">About</a></li>
					<li><a href="#" role="button" data-toggle="modal" data-target=".contact">Contact us</a></li>
					<li><a href="#" role="button" data-toggle="modal" data-target=".help">Help</a></li>
				</ul>
				<security:authorize access="isAuthenticated()">
					<form action="/logout" class="navbar-right navbar-form" method="post">
						<button type="submit" class="btn btn-success navbar-btn">Log Out</button>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" class="form-control" />
					</form>
				</security:authorize>
				<security:authorize access="hasRole('ADMIN')">
					<form action="/admin/countries" class="navbar-right navbar-form">
						<button type="submit" class="btn btn-info navbar-btn">Admin</button>
					</form>
				</security:authorize>
				<security:authorize access="!isAuthenticated()">
					<form action="/registration" class="navbar-right navbar-form">
						<button type="submit" class="btn btn-success navbar-btn">Sign Up</button>
					</form>
					<form action="/login" class="navbar-right navbar-form">
						<button type="submit" class="btn btn-primary navbar-btn">Log In</button>
					</form>
				</security:authorize>
			</div>
		</nav>
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