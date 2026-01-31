<script>
  import { onMount } from 'svelte';
  let products = [];
  let error = null;
  const catalogUrl = 'http://localhost:8080/saas/catalog';
  
  async function fetchProducts() {
    try {
      const response = await fetch(catalogUrl, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          'X-API-KEY': 'SECRET_SAAS_KEY_123'
        }
      });
      if (!response.ok) {
        throw new Error(`Error fetching products: ${response.statusText}`);
      }
      products = await response.json();
    } catch (err) {
      error = err.message;
    }
  }

  onMount(() => {
    fetchProducts();
  });
</script>

<main>
  <h1>Product Catalog</h1>

  {#if error}
    <p class="error">{error}</p>
  {:else if products.length === 0}
    <p>Loading products...</p>
  {:else}
    <div class="product-list">
      {#each products as product}
        <div class="product-item">
            {product.name} {product.color} {product.size} {product.price}€
        </div>
      {/each}
    </div>
  {/if}
</main>

<style>
  main {
    text-align: center;
    font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
    color: #333;
    padding: 2rem;
  }

  h1 {
    font-size: 3rem;
    font-weight: 700;
    color: #2c3e50;
    margin-bottom: 2rem;
  }

  .product-list {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 0.5rem;
  }

  .product-item {
    font-size: 1.2rem;
    color: #2c3e50;
  }

  .error {
    color: #ff3e00;
  }
</style>
