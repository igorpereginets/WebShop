<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="custom" uri="/WEB-INF/custom.tld"%>
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
						<li class="list-group-item"><a href="?categorySearch=${category.name}" class="category"> ${category.name} <span class="badge">${category.count}</span>
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
					<c:forEach items="${brands}" var="brand">
						<li class="list-group-item"><a href="?brandSearch=${brand.name}" class="brands"> ${brand.name} </a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	<div class="col-md-8">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Filter</h3>
			</div>
			<div class="panel-body">
				<form:form action="/" modelAttribute="filter" method="get" name="searchForm">
					<custom:hiddenInputs excludeParams="nameSearch, minPrice, maxPrice, minAmount, maxAmount, brandSearch, categorySearch, tagSearch" />
					<div class="col-md-4 col-sm-12">
						<form:input type="search" path="nameSearch" class="form-control" placeholder="Search name..." />
					</div>
					<div class="col-md-4 col-sm-12">
						<form:input type="number" path="minPrice" class="form-control" placeholder="Min price" />
					</div>
					<div class="col-md-4 col-sm-12">
						<form:input type="number" path="maxPrice" class="form-control" placeholder="Max price" />
					</div>
					<div class="col-md-4 col-sm-12">
						<form:input type="number" path="minAmount" class="form-control" placeholder="Min amount" />
					</div>
					<div class="col-md-4 col-sm-12">
						<form:input type="number" path="maxAmount" class="form-control" placeholder="Max amount" />
					</div>
					<div class="col-md-4 col-sm-12">
						<form:input type="search" path="brandSearch" class="form-control" placeholder="Search producer..." />
					</div>
					<div class="col-md-4 col-sm-12">
						<form:input type="search" path="categorySearch" class="form-control" placeholder="Search category..." />
					</div>
					<div class="col-md-4 col-sm-12">
						<form:input type="search" path="tagSearch" class="form-control" placeholder="Search by tag..." />
					</div>
					<button type="submit" class="btn btn-primary pull-right">Search</button>
				</form:form>
			</div>
		</div>
		<div class="row">
			<c:forEach items="${page.content}" var="good">
				<div class="col-md-6 col-lg-4">
					<div class="thumbnail">
						<a href="/showGoods/${good.id}" class="commodity"> <img class="goodsPhoto" src="${good.pathToFile}" alt="${good.name}">
							<div class="caption">
								<h5>${good.name}</h5>
								<p class="description">${good.shortDescription}</p>
								<p class="pull-left price">${good.price}$</p>
								<p class="text-right toBucket">
									<button class="btn btn-primary" role="button">To bucket</button>
								</p>
							</div>
						</a>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="row">
			<div class="text-center">
				<nav aria-label="Search results pages">
					<ul class="pagination">
						<c:choose>
							<c:when test="${!page.isFirst()}">
								<li><a href="<custom:allParams change="page" value="${page.number}"/>" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
							</c:when>
							<c:otherwise>
								<li class="disabled"><a aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
							</c:otherwise>
						</c:choose>
						<c:forEach begin="1" var="pageNumber" end="${countPages}" varStatus="loop">
							<c:choose>
								<c:when test="${page.number + 1 eq pageNumber}">
									<li class="active"><a href="<custom:allParams change='page' value='${pageNumber}'/>">${pageNumber} <span class="sr-only" /></span></a></li>
								</c:when>
								<c:otherwise>
									<li><a href="<custom:allParams change='page' value='${pageNumber}'/>">${pageNumber} <span class="sr-only" /></span></a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:choose>
							<c:when test="${!page.isLast()}">
								<li><a href="<custom:allParams change="page" value="${page.number + 2}"/>" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
							</c:when>
							<c:otherwise>
								<li class="disabled"><a aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
							</c:otherwise>
						</c:choose>
					</ul>
					<div class="dropup pull-right sort">
						<button class="btn btn-default dropdown-toggle" type="button" id="sorting" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							Sort <span class="caret"></span>
						</button>
						<ul class="dropdown-menu" aria-labelledby="sort">
							<li <custom:isActive key="sort" value="name"/>><a href="<custom:allParams change="sort" value="name"/>"> Name</a></li>
							<li <custom:isActive key="sort" value="price"/>><a href="<custom:allParams change="sort" value="price"/>"> Price</a></li>
						</ul>
					</div>
					<div class="dropup pull-right size">
						<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false">
							Size <span class="caret"></span>
						</button>
						<ul class="dropdown-menu" aria-labelledby="size">
							<c:forEach begin="6" end="24" varStatus="loop" var="sizeNumber" step="6">
								<li <custom:isActive key="size" value="${sizeNumber}"/>><a href="<custom:allParams change="size" value="${sizeNumber}"/>">${sizeNumber}</a></li>
							</c:forEach>
						</ul>
					</div>
				</nav>
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
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Tags</h3>
			</div>
			<div class="panel-body">
				<c:forEach items="${tags}" var="tag">
					<li class="list-group-item"><a href="?tagSearch=${tag.name}"> ${tag.name} </a></li>
				</c:forEach>
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